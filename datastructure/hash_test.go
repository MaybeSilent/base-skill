package datastructure

import (
	"hash/fnv"
	"testing"
)

// Fnv hash算法
func TestFnvHash(t *testing.T) {
	h := fnv.New64()
	_, _ = h.Write([]byte("hello world"))
	res := h.Sum64()
	t.Log(res)

	res1 := FnvHash64("hello world")
	t.Log(res1)
	if res != res1 {
		t.Fatal("error not equal with gopkg hash")
	}
	for i := 1; i <= 10; i++ {
		t.Log(FnvHash64WithNum("hello world", i))
	}

}

// Fnv-a hash算法
func TestFnvaHash(t *testing.T) {
	h := fnv.New64a()
	_, _ = h.Write([]byte("hello world"))
	res := h.Sum64()
	t.Log(res)

	res1 := FnvaHash64("hello world")
	t.Log(res1)
	if res != res1 {
		t.Fatal("error not equal with gopkg hash")
	}
}
