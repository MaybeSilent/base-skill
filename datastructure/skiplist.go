package datastructure

import (
	"math/rand"
	"time"
)

type Comparator interface {
	Less(comparator Comparator) bool
	Equals(comparator Comparator) bool
}

type Node struct {
	Value     Comparator // 跳表当前值
	Level     int        // 当前跳表的层次
	NextNodes []*Node    // 后续节点
}

type SkipList struct {
	random      rand.Rand // 随机数生成器
	maxLevel    int       // 最大节点层数
	head        *Node     // 头节点，无具体值
	probability float32
}

func NewSkipList() *SkipList {
	random := rand.Rand{}
	random.Seed(time.Now().Unix())
	return &SkipList{
		random:      random,
		maxLevel:    1,
		probability: 0.5,
		head: &Node{
			NextNodes: make([]*Node, 1),
		},
	}
}

func (s *SkipList) Add(comparator Comparator) {
	level := s.getLevel() // 获取新节点的level
	if level > s.maxLevel {
		s.maxLevel = level
		s.head.NextNodes = append(s.head.NextNodes, nil)
	}
	// 遍历插入对应的maxLevel节点
	newNode := &Node{Value: comparator, Level: level, NextNodes: make([]*Node, level)}
	curNode := s.head
	for i := s.maxLevel - 1; i >= 0; i-- {
		for curNode.NextNodes[i] != nil && curNode.NextNodes[i].Value.Less(comparator) {
			curNode = curNode.NextNodes[i]
		}
		// 插入相应的节点
		if level > i {
			newNode.NextNodes[i] = curNode.NextNodes[i]
			curNode.NextNodes[i] = newNode
		}
	}
}

// 通过随机数获取当前等级
func (s *SkipList) getLevel() (level int) {
	for level <= s.maxLevel {
		if s.random.Float32() < s.probability {
			level++
		}
	}
	return level
}

func (s *SkipList) Delete(comparator Comparator) {
	curNode := s.head
	for i := s.maxLevel - 1; i >= 0; i-- {
		// 命中相等的情况
		for curNode.NextNodes[i] != nil && curNode.NextNodes[i].Value.Less(comparator) {
			curNode = curNode.NextNodes[i]
		}
		if curNode.NextNodes[i] != nil && curNode.NextNodes[i].Value.Equals(comparator) {
			curNode.NextNodes[i] = curNode.NextNodes[i].NextNodes[i]
		}
	}
}

func (s *SkipList) Contains(comparator Comparator) bool {
	curNode := s.head
	for i := s.maxLevel - 1; i >= 0; i-- {
		for curNode.NextNodes[i] != nil && curNode.NextNodes[i].Value.Less(comparator) {
			curNode = curNode.NextNodes[i]
		}
		if curNode.NextNodes[i] != nil && curNode.NextNodes[i].Value.Equals(comparator) {
			return true
		}
	}
	return false
}
