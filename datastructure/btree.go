package datastructure

import (
	"sync"
	"sync/atomic"

	"github.com/google/btree"
)

// aim to use cow on google's btree to avoid data race
type BTree struct {
	mu   *sync.RWMutex // read and write lock for cow node root change
	tree atomic.Value  // btree of google
}

// new wrapper btree
func NewBTree(degree int) *BTree {
	res := &BTree{
		mu: &sync.RWMutex{},
	}
	res.tree.Store(btree.New(degree))
	return res
}

// insert or update the item in btree
func (b *BTree) InsertOrUpdate(item btree.Item) {
	b.mu.Lock()
	defer b.mu.Unlock()
	cloneTree := b.tree.Load().(*btree.BTree).Clone()
	cloneTree.ReplaceOrInsert(item)
	b.tree.Store(cloneTree)
}

func (b *BTree) Delete(item btree.Item) {
	b.mu.Lock()
	defer b.mu.Unlock()
	cloneTree := b.tree.Load().(*btree.BTree).Clone()
	cloneTree.Delete(item)
	b.tree.Store(cloneTree)
}

// iterate the btree
func (b *BTree) Iterator(iterator btree.ItemIterator) {
	b.tree.Load().(*btree.BTree).Ascend(iterator)
}
