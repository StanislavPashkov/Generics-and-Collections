package ru.netology

object NoteService {
    private var notes = mutableListOf<Note>()


    fun clear() {
        notes = mutableListOf()
    }

    fun presenceNote(id: Int) {
        notes.firstOrNull { note -> note.id == id } ?: throw NoteNotFoundException("Notes with this ID were not found")
    }

    fun addNote(note: Note) {
        if (notes.isEmpty()) {
            note.id = 1
        } else {
            note.id = notes.last().id + 1
        }
        notes.add(note)
        println(notes)
    }

    fun createComment(id: Int, comment: Comment): Comment {
        for (note in notes) {
            if (id == note.id) {
                if (note.comments.isEmpty()) {
                    comment.id = 1
                } else {
                    comment.id = note.comments.last().id + 1
                }
                note.comments.add(comment)
            }

        }
        return comment

    }

    fun delete(id: Int): Boolean {
        presenceNote(id)
        notes.removeAt(id - 1)
        return true
    }

    //    fun delete(id: Int): MutableList<Note> {
//        val iterator = notes.iterator()
//        while (iterator.hasNext()) {
//            if (id == iterator.next().id) {
//                iterator.remove()
//                return notes
//            }
//
//        }
//        throw NoteNotFoundException("заметки нет")
//
//    }
//    fun deleteComment(noteId: Int, commentId: Int): Boolean {
//        val comments = mutableListOf<Comment>()
//
//        notes.firstOrNull { note -> note.id == noteId } ?: false//?: throw NoteNotFoundException("заметки нет")
//        notes.firstOrNull { comment -> comment.id == commentId } ?: false
//        //?: throw NoteNotFoundException("коментария нет или коментарий уже удален")
//        //comments.find { it.id == commentId }?.isDeleted = true
//        return true
//    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        presenceNote(noteId)
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDeleted) {
                        comment.isDeleted = true
                        return true
                    }
                }
            }
        }
        throw NoteNotFoundException("There is no comment with this ID")
    }

    fun edit(id: Int, title: String, text: String): Boolean {
        for (note in notes) {
            if (id == note.id) {
                note.title = title
                note.text = text
                return true
            }
        }
        throw NoteNotFoundException("Notes with this ID were not found")
    }

    fun editComment(noteId: Int, commentId: Int, text: String): Boolean {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDeleted) {
                        comment.text = text
                        return true
                    }
                }
                throw NoteNotFoundException("There is no comment with this ID")
            }
        }
        throw NoteNotFoundException("Notes with this ID were not found")
    }

    fun getNotes(): List<Note> {
        return notes.ifEmpty {
            throw NoteNotFoundException("Notes with this ID were not found")
        }
    }

    fun getById(id: Int): Note {
        for (note in notes) {
            if (id == note.id) {
                return note
            }
        }
        throw NoteNotFoundException("Notes with this ID were not found")
    }

    fun getComments(noteId: Int): List<Comment> {
        val comments = mutableListOf<Comment>()
        for (note in notes) {
            if (note.id == noteId) {

                for (comment in note.comments) {
                    if (!comment.isDeleted) {
                        comments.add(comment)
                    }

                }
                return comments
            }
        }
        throw NoteNotFoundException("There is no comment with this ID")
    }

    fun restoreComment(noteId: Int, commentId: Int) {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId && comment.isDeleted) {
                        comment.isDeleted = false
                    }
                }
            }
        }
    }

    fun printNote() {

        println(notes)
        println("***********************************************************")
    }
}


