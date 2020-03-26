package gooner.demo.api

class Result<T>(
    var data: T?,
    var error: Throwable?
)
