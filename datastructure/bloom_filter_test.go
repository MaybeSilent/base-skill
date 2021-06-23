package datastructure

import (
	"math"
	"math/rand"
	"testing"
	"time"
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

// 测试布隆过滤器
func TestBloomFilter(t *testing.T) {
	randStrMap := make(map[string]struct{}, 0)
	for i := 0; i < 65536; i++ {
		randStrMap[genRandomStr(10)] = struct{}{}
	}
	t.Log(len(randStrMap))

	bloomFilter, err := NewBloomFilter(65536, 0.01)
	if err != nil {
		t.Fatal(err)
	}
	for k, _ := range randStrMap {
		bloomFilter.Add(k)
	}

	for k, _ := range randStrMap {
		if !bloomFilter.Contains(k) {
			t.Fatal("Error with Bloom Filter")
		}
	}
	t.Log("Test PASS")

	// 计算fpp
	notExistStrMap := make(map[string]struct{}, 0)
	for i := 0; i < 100000; i++ {
		str := genRandomStr(10)
		if _, ok := randStrMap[str]; !ok {
			notExistStrMap[str] = struct{}{}
		}
	}
	t.Log("notExist:", len(notExistStrMap))

	errCount := 0
	for k, _ := range notExistStrMap {
		if bloomFilter.Contains(k) {
			errCount++
		}
	}
	t.Log("errCount:", errCount)
	t.Log("fpp:", float64(errCount)/float64(100000))
}

// 生成随机字符串的字母
const letterBytes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

func init() {
	rand.Seed(time.Now().UnixNano())
}

// 生成大小为n的随机数
func genRandomStr(n int) string {
	b := make([]byte, n)
	for i := range b {
		b[i] = letterBytes[rand.Intn(len(letterBytes))]
	}
	return string(b)
}
