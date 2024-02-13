package ru.netology

open class Note (
    var id: Int = 0,
    var title: String,
    var text: String,
    val date: Int = 0,
    var comments: MutableList<Comment> = mutableListOf(),
    val canComment: Boolean = true


){
    override fun toString(): String {
        return "$id,$title,$text,$date,$comments,$canComment\n"
    }
}
