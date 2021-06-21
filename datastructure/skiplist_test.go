package datastructure

import (
	"math/rand"
	"testing"
	"time"
)

// 普通列表，非跳表，比较两者性能
type compareList struct {
	compare []int
}

func NewCompareList() *compareList {
	return &compareList{compare: []int{}}
}

func (c *compareList) add(value int) {
	c.compare = append(c.compare, value)
}

func (c *compareList) delete(value int) {
	for i := 0; i < len(c.compare); i++ {
		if c.compare[i] == value {
			c.compare = append(c.compare[:i], c.compare[i+1:]...)
			return
		}
	}
}

func (c *compareList) contains(value int) bool {
	for i := 0; i < len(c.compare); i++ {
		if c.compare[i] == value {
			return true
		}
	}
	return false
}

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

// 测试 SkipList 的插入功能
func TestAddSkipList(t *testing.T) {
	s := NewSkipList()
	random := rand.New(rand.NewSource(time.Now().Unix()))
	var nums = make([]int, 100)
	for i := 0; i < 100; i++ {
		nums[i] = random.Intn(100)
	}
	t.Log(nums)

	for i := 0; i < 100; i++ {
		s.Add(nums[i])
	}

	level := 0
	res := levelPrint(s, level)
	t.Log(res)
	for len(res) != 0 {
		level++
		res = levelPrint(s, level)
		t.Log(res)
	}

	t.Log(s)
}

func levelPrint(s *SkipList, level int) []int {
	var res []int
	if level >= s.maxLevel {
		return res // 返回空数组
	}
	curNode := s.head.NextNodes[level]
	for curNode != nil {
		res = append(res, curNode.Value)
		curNode = curNode.NextNodes[level]
	}
	return res
}

// 测试 SkipListContains
func TestSkipListContain(t *testing.T) {
	s := NewSkipList()
	c := NewCompareList()
	random := rand.New(rand.NewSource(time.Now().Unix()))
	var nums = make([]int, 100)
	for i := 0; i < 100; i++ {
		nums[i] = random.Intn(100)
		s.Add(nums[i])
		c.add(nums[i])
	}

	for i := 0; i < 1000; i++ {
		val := random.Intn(100)
		skipContain := s.Contains(val)
		compareContain := c.contains(val)
		if skipContain != compareContain {
			t.Log("ERROR")
		}
	}
}

// benchmark 测试
func BenchmarkContain(b *testing.B) {
	s := NewSkipList()
	c := NewCompareList()
	random := rand.New(rand.NewSource(time.Now().Unix()))

	// 数据规模
	all := 1000000 // 规模越大越明显,100等小规模速度差别不大
	var nums = make([]int, all)
	for i := 0; i < all; i++ {
		nums[i] = random.Intn(all)
		s.Add(nums[i])
		c.add(nums[i])
	}
	b.ResetTimer()

	b.Run("SkipList Contains", func(b *testing.B) {
		for i := 0; i < b.N; i++ {
			val := random.Intn(all)
			s.Contains(val)
		}
	})

	b.Run("Compare Contains", func(b *testing.B) {
		for i := 0; i < b.N; i++ {
			val := random.Intn(all)
			c.contains(val)
		}
	})
}

// 测试 SkipList 的delete
func TestSkipListDelete(t *testing.T) {
	s := NewSkipList()
	c := NewCompareList()
	random := rand.New(rand.NewSource(time.Now().Unix()))

	all := 10000
	for i := 0; i < all; i++ {
		num := random.Intn(all)
		s.Add(num)
		c.add(num)
	}

	half := all / 2
	for i := 0; i < half; i++ {
		num := random.Intn(all)
		s.Delete(num)
		c.delete(num)
	}

	for i := 0; i < all*10; i++ {
		val := random.Intn(all)
		skipContain := s.Contains(val)
		compareContain := c.contains(val)
		if skipContain != compareContain {
			t.Log("ERROR")
		}
	}
}
