package datastructure

import (
	"math"
	"testing"
)

func TestGetNextPowerOf2(t *testing.T) {
	caseValue := []uint32{3, 5, 23, 1, 0}
	assertValue := []uint32{4, 8, 32, 1, 0}
	for i, v := range caseValue {
		value := getNextPowerOf2(v)
		if value != assertValue[i] {
			t.Log(value)
			t.Log(assertValue[i])
			t.Fatal("Not Equal")
		}
	}
	t.Log("Finish Test")
}

func TestLn2(t *testing.T) {
	t.Log(math.Ln2)
	t.Log(math.Log(2))
}
