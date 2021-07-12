package datastructure

// 外部类需要实现KeyType接口，作为元素进行插入
type Key interface {
	LessThan(interface{}) bool
}

type Value interface{}

const Red = 1
const Black = 0

type node struct {
	left, right, parent *node
	color               int
	key                 Key
	value               Value
}

// 红黑树的定义
type Tree struct {
	root *node
	size int
}

// 红黑树查找代码
func (t *Tree) findNode(key Key) *node {
	x := t.root
	for x != nil {
		if key.LessThan(t.root.key) {
			x = x.left
		} else {
			if key == x.key {
				return x
			}
			x = x.right
		}
	}
	return nil
}

// 红黑树插入的代码
func (t *Tree) Insert(key Key, value Value) {
	x := t.root

	var parentNode *node
	for x != nil {
		parentNode = x
		if key.LessThan(x.key) {
			x = x.left
		} else {
			x = x.right
		}
	}

	insertNode := &node{key: key, value: value, parent: parentNode, color: Red}
	if parentNode == nil {
		t.root = insertNode
		insertNode.color = Black
		return
	} else if key.LessThan(parentNode.key) {
		parentNode.left = insertNode
	} else {
		parentNode.right = insertNode
	}

	// 调整树的整体结构
	t.fixTreeWhenInsert(insertNode)
}

// 插入时进行结构调整
func (t *Tree) fixTreeWhenInsert(insert *node) {
	// 插入的节点为红色，可能会出现两个红色节点在一起的情况，违反了相关性质
	if insert.parent != nil && insert.parent.color == Red {
		if insert.parent == insert.parent.parent.left {
			uncleNode := insert.parent.parent.right
			if uncleNode != nil && uncleNode.color == Red { // 父节点与叔叔节点都为红色的情况
				insert.parent.color, uncleNode.color = Black, Black
				insert.parent.parent.color = Red
				insert = insert.parent.parent
			} else { // 叔叔节点为黑色的情况下处理
				if insert == insert.parent.right { // 对于需要
					insert = insert.parent
					t.leftRotate(insert)
				}
				insert.parent.color = Black
				insert.parent.parent.color = Red
				t.rightRotate(insert.parent.parent)
			}
		} else { // 属于右节点的情况，对称操作
			uncleNode := insert.parent.parent.left
			if uncleNode != nil && uncleNode.color == Red { // 叔叔节点为红色的情况
				insert.parent.color, uncleNode.color = Black, Black
				insert.parent.parent.color = Red
				insert = insert.parent.parent
			} else { // 叔叔节点为黑色的情况处理
				if insert == insert.parent.left { //
					insert = insert.parent
					t.rightRotate(insert)
				}
				insert.parent.color = Black
				insert.parent.parent.color = Red
				t.leftRotate(insert.parent.parent)
			}
		}
	}

	t.root.color = Black
}

// 红黑树删除的代码
func (t *Tree) Delete(key Key) {
	keyNode := t.findNode(key)
	if keyNode == nil {
		return
	}

	var deleteNode, nextNode *node
	if keyNode.left != nil && keyNode.right != nil {
		deleteNode = successor(keyNode)
		keyNode.key = deleteNode.key     // 利用后继节点替换当前节点
		keyNode.value = deleteNode.value // key，value进行替换
	} else {
		deleteNode = keyNode
	}

	if deleteNode.left != nil {
		nextNode = deleteNode.left
	} else {
		nextNode = deleteNode.right
	}

	parentNode := deleteNode.parent
	if nextNode != nil {
		nextNode.parent = parentNode // 将下一个节点设置为
	}
	// 设置相应的parent
	if parentNode == nil {
		t.root = nextNode // 树的跟节点为nextNode
	} else if deleteNode == parentNode.left {
		parentNode.left = nextNode
	} else {
		parentNode.right = nextNode
	}

	if deleteNode.color == Black { // 红黑树中删除红色节点并不会改变节点性质
		t.fixTreeWhenDelete(nextNode, parentNode)
	}

	t.size-- // 树的大小 -1
}

// 进行删除节点的树结构调整
func (t *Tree) fixTreeWhenDelete(newNode, parent *node) {
	// 删除节点会破坏相关树的结构
	for newNode != t.root && getColor(newNode) == Black { // 替代的节点为黑色的情况，节点路径上缺少了黑色的节点
		if newNode != nil {
			parent = newNode.parent
		}

		if newNode == parent.left {
			uncleNode := parent.right
			if uncleNode.color == Red {
				uncleNode.color = Black
				parent.color = Red
				t.leftRotate(parent)
				uncleNode = parent.right
			}

			if getColor(uncleNode.left) == Black && getColor(uncleNode.right) == Black {
				uncleNode.color = Red
				newNode = parent
			} else { // 红色节点一定为有颜色的节点，并不会为nil
				if getColor(uncleNode.right) == Black {
					uncleNode.left.color = Black
					uncleNode.color = Red
					t.rightRotate(uncleNode)
					uncleNode = parent.right
				}
				// 红黑树右节点为红色的情况
				uncleNode.color = parent.color
				parent.color, uncleNode.right.color = Black, Black
				t.leftRotate(parent)
				newNode = t.root // 结束循环
			}
		} else { // 删除的黑色节点在父节点的右子树
			uncleNode := parent.left // x本身为黑色，uncle节点不可能为nil
			if uncleNode.color == Red {
				uncleNode.color = Black
				parent.color = Red
				t.rightRotate(parent)
				uncleNode = parent.left
			}

			if getColor(uncleNode.left) == Black && getColor(uncleNode.right) == Black {
				uncleNode.color = Red
				newNode = parent
			} else {
				if getColor(uncleNode.left) == Black {
					uncleNode.right.color = Black
					uncleNode.color = Red
					t.leftRotate(uncleNode)
					uncleNode = parent.left
				}
				// 进行右旋调整树结构
				uncleNode.color = parent.color
				parent.color, uncleNode.left.color = Black, Black
				t.rightRotate(parent)
				newNode = t.root
			}
		}
	}

	if newNode != nil { // 根结点或者替代的子节点为红色，直接将相关的子节点置为黑色
		newNode.color = Black
	}
}

// 获取节点颜色
func getColor(x *node) int {
	if x == nil {
		return Black
	}
	return x.color
}

// 红黑树的左旋与右旋
// 左旋
func (t *Tree) leftRotate(x *node) {
	y := x.right

	x.right = y.left
	if y.left != nil {
		y.left.parent = x
	}
	y.parent = x.parent
	if x.parent == nil {
		t.root = y
	} else if x == x.parent.left {
		x.parent.left = y
	} else {
		x.parent.right = y
	}

	y.left = x
	x.parent = y
}

// 右旋
func (t *Tree) rightRotate(x *node) {
	y := x.left

	x.left = y.right
	if y.right != nil {
		y.right.parent = x
	}

	y.parent = x.parent
	if x.parent == nil {
		t.root = y
	} else if x.parent.left == x {
		x.parent.left = y
	} else {
		x.parent.right = y
	}

	y.right = x
	x.parent = y
}

// 查找后继者的代码，查找node的后继者（值）
func successor(node *node) *node {
	if node.right != nil {
		return minimum(node)
	}

	x := node.parent
	if x != nil && x.right == node {
		node = x
		x = node.parent
	}
	return x
}

func minimum(node *node) *node {
	for node.left != nil {
		node = node.left
	}
	return node
}
