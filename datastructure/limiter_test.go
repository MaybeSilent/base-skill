package datastructure

import (
	"sync/atomic"
	"testing"
	"time"
)

func TestNewLimiter(t *testing.T) {
	l := NewLimiter(10, 100*time.Millisecond)
	time.Sleep(time.Second)
	var count int32
	for i := 0; i < 100; i++ {
		go func(countAddr *int32) {
			if l.Pass() {
				atomic.AddInt32(countAddr, 1)
			}
		}(&count)
	}
	t.Log("pass count:", count)

	time.Sleep(time.Second * 2)
	for i := 0; i < 100; i++ {
		go func(countAddr *int32) {
			if l.Pass() {
				atomic.AddInt32(countAddr, 1)
			}
		}(&count)
	}
	t.Log("pass count:", count)
}
