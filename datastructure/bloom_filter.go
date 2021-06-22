package datastructure

import "math"

// 布隆过滤器
type BloomFilter struct {
	NumHash int // hash函数的个数

	numBit []uint64 // 布隆过滤器数组个数
}

// 构造布隆过滤器，n为预计的过滤器存放的数量，fpp为预计的的布隆过滤器误判率
func NewBloomFilter(n int, fpp float64) *BloomFilter {
	// 可根据公式计算
	caculateLen := uint32(-float64(n) * float64(n) * math.Log(fpp) / (math.Ln2 * math.Ln2))
	resLen := getNextPowerOf2(caculateLen)
	if resLen <= 1 {
		resLen = 1
	}
	arrayLen := (resLen + 63) / 64                            // numsBit的数组长度
	numHash := int(math.Ln2 * float64(arrayLen) / float64(n)) // hash函数也可根据计算公式进行计算
	return &BloomFilter{
		NumHash: numHash,
		numBit:  make([]uint64, arrayLen),
	}
}

func (b *BloomFilter) Add(val uint64) {

}

func (b *BloomFilter) Contains(val uint64) bool {
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
	return i + 1
}
