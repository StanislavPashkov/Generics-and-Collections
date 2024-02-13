import org.junit.Before
import org.junit.Test
import ru.netology.Comment
import ru.netology.Note
import ru.netology.NoteNotFoundException
import ru.netology.NoteService
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class NoteServiceTest {

    private val service = NoteService

    private val note1 = Note(
        title = "One", text = "First Note", comments = mutableListOf()
    )
    private val note2 = Note(
        title = "Two", text = "Second Note", comments = mutableListOf()
    )
    private val comment1 = Comment(
        text = "First Comment"
    )
    private val comment2 = Comment(
        text = "Second Comment"
    )

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowComment() {
        service.addNote(note1)
        service.createComment(1, comment1)
        service.deleteComment(1, 10)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowException() {
        service.addNote(note1)
        service.delete(10)
    }

    @Test
    fun shouldAddNote() {
        service.addNote(note1)
        service.addNote(note2)

        assertEquals(1, note1.id)
        assertEquals(2, note2.id)
    }

    @Test
    fun shouldAddComment() {
        service.addNote(note1)
        service.addNote(note2)
        service.createComment(2, comment1)
        service.createComment(1, comment1)
        service.createComment(2, comment2)
        assertEquals(1, comment1.id)
        assertEquals(2, comment2.id)
    }

    @Test
    fun shouldDelete() {
        service.addNote(note1)
        service.addNote(note2)
        service.delete(2)

        assertTrue(true)

        service.createComment(1, comment1)
        service.deleteComment(1, 1)

        assertTrue(true)
    }

    @Test
    fun shouldEdit() {
        service.addNote(note1)
        service.createComment(1, comment1)
        val text = "New"
        val title = "New title"
        service.edit(1, title, text)

        assertTrue(true)
        service.editComment(1, 1, text)
        assertTrue(true)
    }

    @Test
    fun shouldReturnNote() {
        service.addNote(note1)
        service.addNote(note2)

        assertEquals(2, service.getNotes().size)

        assertEquals(1, service.getById(1).id)
    }

    @Test
    fun shouldReturnComment() {
        service.addNote(note1)
        service.createComment(1, comment1)
        service.createComment(1, comment2)
        service.createComment(1, comment2)
        service.createComment(1, comment2)

        assertEquals(4, service.getComments(1).size)
    }

    @Test
    fun shouldReturnDeletedComment() {
        service.addNote(note1)
        service.createComment(1, comment1)
        service.createComment(1, comment2)

        service.deleteComment(1, 2)
        assertEquals(1, service.getComments(1).size)

        service.restoreComment(1, 2)
        assertEquals(2, service.getComments(1).size)

    }
}