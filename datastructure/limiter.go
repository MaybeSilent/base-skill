package datastructure

import (
	"sync/atomic"
	"time"
)

type Limiter struct {
	QPS       int32
	Remaining int32 // 剩余的可以通过的数量
}

func NewLimiter(QPS int) *Limiter {
	if QPS < 0 {
		QPS = 0
	}
	res := &Limiter{
		QPS: int32(QPS),
	}
	go func() {
		res.refresh()
	}()
	return res
}

func (l *Limiter) refresh() {
	// 更新时间根据
	addInt := l.QPS / 10
	if addInt == 0 && !(l.QPS == 0) {
		addInt = 1
	}

	addTicker := time.Tick(time.Millisecond * 100)
	for {
		<-addTicker //
		if atomic.LoadInt32(&l.Remaining) > l.QPS {
			continue
		}
		atomic.AddInt32(&l.Remaining, addInt)
	}
}

func (l *Limiter) Pass() bool {
	if atomic.LoadInt32(&l.Remaining) > 0 {
		atomic.AddInt32(&l.Remaining, -1)
		return true
	}
	return false
}
