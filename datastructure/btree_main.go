// main function used for go run
// +build ignore

package main

import (
	"math/rand"

	"github.com/google/btree"

	"github.com/maybesilent/datastructure"
)

func main() {
	tree := datastructure.NewBTree(8)
	list := rand.Perm(10) // 生成rand的随机序列

	deleteList := rand.Perm(5) // 生成delete的随机序列

	go func() {
		for _, num := range list {
			tree.InsertOrUpdate(btree.Int(num))
		}
	}()

	go func() {
		for _, num := range deleteList {
			tree.Delete(btree.Int(num))
		}
	}()

	tree.Iterator(func(i btree.Item) bool {
		return true
	})
}
