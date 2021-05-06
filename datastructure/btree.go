package datastructure

import (
	"sync"

	"github.com/google/btree"
)

// aim to use cow on google's btree to avoid data race
type BTree struct {
	mu   *sync.RWMutex // read and write lock for cow node root change
	tree *btree.BTree  // btree of google
}

// new wrapper btree
func NewBTree(degree int) *BTree {
	return &BTree{
		mu:   &sync.RWMutex{},
		tree: btree.New(degree),
	}
}

// insert or update the item in btree
func (b *BTree) InsertOrUpdate(item btree.Item) {
	b.tree.ReplaceOrInsert(item)
}

func (b *BTree) Delete(item btree.Item) {
	b.tree.Delete(item)
}

// iterate the btree
func (b *BTree) Iterator(iterator btree.ItemIterator) {
	b.tree.Ascend(iterator)
}
