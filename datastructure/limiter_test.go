package datastructure

import (
	"math/rand"
	"testing"
	"time"
)

func TestNewLimiter(t *testing.T) {
	l := NewLimiter(10)
	for i := 0; i < 20; i++ {
		time.Sleep(time.Millisecond * time.Duration(rand.Int31n(5)))
		if l.Pass() {
			t.Log("pass")
		} else {
			t.Log("noPass")
		}
	}
}
