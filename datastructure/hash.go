package datastructure

const prime32 = 16777619
const prime64 = 1099511628211

// 初始化offset
const offset32 = 2166136261
const offset64 = 14695981039346656037

// Fnv的hash方法
func FnvHash64(s string) uint64 {
	res := uint64(offset64)
	for _, b := range []byte(s) {
		res *= prime64
		res ^= uint64(b)
	}
	return res
}

// Fnv-a的hash方法
func FnvaHash64(s string) uint64 {
	res := uint64(offset64)
	for _, b := range []byte(s) {
		res ^= uint64(b)
		res *= prime64
	}
	return res
}

// Fnv with nums
func FnvHash64WithNum(s string, num int) uint64 {
	res := uint64(offset64)
	for _, b := range []byte(s) {
		res *= prime64 * uint64(num)
		res ^= uint64(b)
	}
	return res
}

// Fnv的hash方法
func FnvHash32(s string) uint32 {
	res := uint32(offset32)
	for _, b := range []byte(s) {
		res *= prime32
		res ^= uint32(b)
	}
	return res
}

// Fnv with nums
func FnvHash32WithNum(s string, num int) uint32 {
	res := uint32(offset32)
	for _, b := range []byte(s) {
		res *= prime32 * uint32(num)
		res ^= uint32(b)
	}
	return res
}
