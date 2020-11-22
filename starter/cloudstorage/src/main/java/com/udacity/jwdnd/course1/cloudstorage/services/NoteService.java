package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(Integer userId) {
        return noteMapper.getNotes(userId);
    }

    public int createOrUpdateNote(Note note, Integer userid, Integer noteid) {
        note.setUserid(userid);

        if(noteid != null) {
            return noteMapper.update(note);
        }

        return noteMapper.insert(note);
    }

    public int deleteNote(Note note, Integer userid) {
        note.setUserid(userid);
        return noteMapper.delete(note);
    }
}