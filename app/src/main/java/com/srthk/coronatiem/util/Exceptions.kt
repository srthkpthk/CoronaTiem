package com.srthk.coronatiem.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class InternetNotAvailableException(message: String) : IOException(message)