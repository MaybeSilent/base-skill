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
