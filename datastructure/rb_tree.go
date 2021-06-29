package datastructure

// 外部类需要实现KeyType接口，作为元素进行插入
type Key interface {
	LessThan(interface{}) bool
}

type value interface{}

type node struct {
	left, right, parent *node
	color               int
	key                 Key
	value               value
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
		if key.LessThan(t.root) {
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
func (t *Tree) Insert() {

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
		return minimun(node)
	}

	x := node.parent
	if x != nil && x.right == node {
		node = x
		x = node.parent
	}
	return x
}

func minimun(node *node) *node {
	for node.left != nil {
		node = node.left
	}
	return node
}
