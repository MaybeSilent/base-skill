package datastructure

import (
	"errors"
	"math"
)

// 布隆过滤器
type BloomFilter struct {
	NumHash int // hash函数的个数

	numBit  []uint64 // 布隆过滤器数组个数
	bitMask uint32
}

// 构造布隆过滤器，n为预计的过滤器存放的数量，fpp为预计的的布隆过滤器误判率
func NewBloomFilter(n int, fpp float64) (*BloomFilter, error) {
	// 可根据公式计算
	caculateLen := uint32(-float64(n) * math.Log(fpp) / (math.Ln2 * math.Ln2))
	resLen := getNextPowerOf2(caculateLen)
	arrayLen := resLen / 64                                        // numsBit的数组长度(+1防止溢出的情况)
	numHash := int(math.Ln2 * (float64(caculateLen) / float64(n))) // hash函数也可根据计算公式进行计算

	if numHash == 0 || resLen == 0 || arrayLen == 0 {
		return nil, errors.New("InitBloomFilterError")
	}

	return &BloomFilter{
		NumHash: numHash,
		numBit:  make([]uint64, arrayLen), // 数组最大为 2 ** 32 - 1 bit，即512MB内存
		bitMask: resLen,
	}, nil
}

// 布隆过滤器增加值
func (b *BloomFilter) Add(val string) {
	for i := 1; i <= b.NumHash; i++ {
		hash := FnvHash32WithNum(val, i)
		hashIndex := hash % b.bitMask
		index := hashIndex / 64
		bits := hashIndex % 64
		b.numBit[index] |= uint64(1 << bits)
	}
}

func (b *BloomFilter) Contains(val string) bool {
	for i := 1; i <= b.NumHash; i++ {
		hash := FnvHash32WithNum(val, i)
		hashIndex := hash % b.bitMask
		index := hashIndex / 64
		bits := hashIndex % 64
		if b.numBit[index]&uint64(1<<bits) == 0 {
			return false
		}
	}
	return true
}

// 获取大于此值的2的幂等最小值
func getNextPowerOf2(i uint32) uint32 {
	i--
	i |= i >> 1
	i |= i >> 2
	i |= i >> 4
	i |= i >> 8
	i |= i >> 16
	if i == math.MaxUint32 {
		return i
	}
	return i + 1
}
