package datastructure

import (
	"github.com/google/btree"
	"sync"
)

type BTree struct {
	mu sync.RWMutex	// read and write lock for cow node root change
	tree *btree.BTree	//
}
// 测试BTree
func NewBTree() {

}

//
func Iterator() {

}