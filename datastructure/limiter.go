package datastructure

import (
	"sync/atomic"
	"time"
)

type Limiter struct {
	QPS       int32
	Remaining int32         // 剩余的可以通过的数量
	Interval  time.Duration // 每次更新限流器的时间间隔
}

func NewLimiter(QPS int, refreshInterval time.Duration) *Limiter {
	if QPS < 0 {
		QPS = 0
	}
	// 限流器时间设置
	if refreshInterval < time.Millisecond*10 {
		refreshInterval = time.Millisecond * 10
	}
	if refreshInterval > time.Second {
		refreshInterval = time.Second
	}
	res := &Limiter{
		QPS:      int32(QPS),
		Interval: refreshInterval,
	}
	go func() {
		res.refresh()
	}()
	return res
}

func (l *Limiter) refresh() {
	// 更新时间根据
	addInt := l.QPS / int32(time.Second/l.Interval)
	if addInt == 0 && !(l.QPS == 0) {
		addInt = 1
	}

	addTicker := time.Tick(l.Interval)
	for {
		<-addTicker // 根据l.Interval进行触发
		if atomic.LoadInt32(&l.Remaining)+addInt > l.QPS {
			continue
		}
		atomic.AddInt32(&l.Remaining, addInt)
	}
}

func (l *Limiter) Pass() bool {
	if atomic.LoadInt32(&l.Remaining) > 0 { // 并发扣减分开，会有击穿的风险
		return atomic.AddInt32(&l.Remaining, -1) >= 0
	}
	return false
}
