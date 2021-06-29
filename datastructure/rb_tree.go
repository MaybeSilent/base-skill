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
func (t *Tree) Delete() {

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
