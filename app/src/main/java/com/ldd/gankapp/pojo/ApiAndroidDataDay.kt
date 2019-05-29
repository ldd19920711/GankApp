package com.ldd.gankapp.pojo

class ApiAndroidDataDay {

    var error: Boolean = false
    var results: List<ResultsBean>? = null


    override fun toString(): String {
        return "ApiAndroidDataDay{" +
                "error=" + error +
                ", results=" + results +
                '}'.toString()
    }

    class ResultsBean {

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var used: Boolean = false
        var who: String? = null
        var images: List<String>? = null


        override fun toString(): String {
            return "ResultsBean{" +
                    "_id='" + _id + '\''.toString() +
                    ", createdAt='" + createdAt + '\''.toString() +
                    ", desc='" + desc + '\''.toString() +
                    ", publishedAt='" + publishedAt + '\''.toString() +
                    ", source='" + source + '\''.toString() +
                    ", type='" + type + '\''.toString() +
                    ", url='" + url + '\''.toString() +
                    ", used=" + used +
                    ", who='" + who + '\''.toString() +
                    ", images=" + images +
                    '}'.toString()
        }
    }
}
