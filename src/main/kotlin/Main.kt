package ru.netology

fun main() {
    val note1 = Note(title = "One", text = "First Note")
    val note2 = Note(title = "Two", text = "Second Note")
    val note3 = Note(title = "Three", text = "Third Note")

    val comment1 = Comment(text = "First Comment")
    val comment2 = Comment(text = "Second Comment")

    val noteService = NoteService

    noteService.addNote(note1)
    noteService.addNote(note2)
//    noteService.printNote()
//   noteService.delete(1)
//
//    NoteService.printNote()
    noteService.createComment(1,comment1)
    noteService.createComment(2,comment2)
//    noteService.printNote()
//    noteService.delete(1)
//    noteService.printNote()
    println( noteService.deleteComment(1,1))
    noteService.printNote()
//   noteService.deleteComment(1,2)
//   noteService.printNote()


//    println(noteService.addNote(note3))
//    NoteService.printNote()
//
//
//    println(noteService.createComment(1, comment2))
//    noteService.deleteComment(1, 1)
//    println(noteService.createComment(2, comment1))
//    noteService.printNote()
//
//    noteService.delete(2)
//    noteService.printNote()
//    println(noteService.deleteComment(1, 1))
//    println(noteService.getById(1))
//    noteService.printNote()

}