package datastructure

import (
	"math/rand"
	"testing"
	"time"
)

// 实现随机函数
func TestRandom(t *testing.T) {
	rand.Seed(time.Now().Unix())
	for i := 0; i < 10; i++ {
		t.Log("Int:", rand.Int())
		t.Log("Int31:", rand.Int31())
		t.Log("Float32:", rand.Float32())
		t.Log("Float64:", rand.Float64())
	}
}

func TestSliceMake(t *testing.T) {
	s := make([]int, 5)
	s[0] = 1
	t.Log(s[0])
	t.Log(len(s))
	t.Log(cap(s))
}
