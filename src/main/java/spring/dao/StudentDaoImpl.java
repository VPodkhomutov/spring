package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import spring.StudentMark;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public StudentDaoImpl(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer getMarkByFioStudent(String fio) {
        int tmpMark;
        try {
            tmpMark = parameterJdbcTemplate.queryForObject("select mark from students where fio = :fio limit 1", Collections.singletonMap("fio", fio), Integer.class);
        } catch (DataAccessException e) {
            tmpMark=0;
        }
        return tmpMark;
    }

    @Override
    public void addStudents(List<StudentMark> studentMarks) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(studentMarks);
        parameterJdbcTemplate.batchUpdate("insert into students (fio, mark) values(:fio, :mark)", params);
    }
}
