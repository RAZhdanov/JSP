package model;

import java.sql.Blob;

public class Book {
    private int _book_id;
    private String _isbn;
    private String _title;
    private String _author;
    private String _editor;
    private String _edition;
    private float _price;
    private String _description;
    private Blob _Blob;

    public Book(int _book_id, String _isbn, String _title, String _author, String _editor, String _edition, float _price, String _description, Blob _Blob) {
        this._book_id = _book_id;
        this._isbn = _isbn;
        this._title = _title;
        this._author = _author;
        this._editor = _editor;
        this._edition = _edition;
        this._price = _price;
        this._description = _description;
        this._Blob = _Blob;
    }

    public int getBook_id() {
        return _book_id;
    }

    public String getIsbn() {
        return _isbn;
    }

    public String getTitle() {
        return _title;
    }

    public String getAuthor() {
        return _author;
    }

    public String getEditor() {
        return _editor;
    }

    public String getEdition() {
        return _edition;
    }

    public float getPrice() {
        return _price;
    }

    public String getDescription() {
        return _description;
    }


    public void setBook_id(int _book_id) {
        this._book_id = _book_id;
    }

    public void setIsbn(String _isbn) {
        this._isbn = _isbn;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public void setAuthor(String _author) {
        this._author = _author;
    }

    public void setEditor(String _editor) {
        this._editor = _editor;
    }

    public void setEdition(String _edition) {
        this._edition = _edition;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public void setBlob(Blob _Blob) {
        this._Blob = _Blob;
    }
}
