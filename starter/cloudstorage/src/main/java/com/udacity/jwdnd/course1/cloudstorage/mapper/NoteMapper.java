package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<Note> getNotes(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (notetitle, notedescription, userid) VALUES(#{filename}, #{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);
}

