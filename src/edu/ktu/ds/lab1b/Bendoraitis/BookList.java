/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.Bendoraitis;

import edu.ktu.ds.lab1b.util.ParsableList;

/**
 *
 * @author Antanas
 */
public class BookList extends ParsableList<Book> {

    public BookList() {
    }
    @Override
    protected Book createElement(String data)
    {
        return new Book(data);
    }
}
