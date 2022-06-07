package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.IGroupManager;
import com.artech.timetableapp.core.manager.ISpecialityManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Медеджер моделей групп
 */
public final class GroupManager extends DbObjectManager<GroupModel> implements IGroupManager {

    private final ISpecialityManager specialityManager;

    /**
     * Конструктор менеджера моделей групп
     * @param handle Дескриптор БД
     * @param specialityManager Менеджер моделей специальностей
     */
    public GroupManager(DatabaseHandle handle, ISpecialityManager specialityManager) throws SQLException {
        super(handle, "groups");
        this.specialityManager = specialityManager;
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS groups (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "semester INTEGER NOT NULL," +
                "num_study_weeks INTEGER NOT NULL," +
                "speciality INTEGER REFERENCES specialities(id) ON DELETE SET NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected GroupModel build(ResultSet result) {
        try {
            return new GroupModel(
                    result.getInt("id"),
                    result.getString("name"),
                    specialityManager.get(getInt(result,"speciality")),
                    result.getInt("semester"),
                    result.getInt("num_study_weeks")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(GroupModel prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO groups (name, speciality, semester, num_study_weeks)" +
                    " VALUES (?, ?, ?, ?)");
            statement.setString(1, prototype.name());
            statement.setInt(2, prototype.speciality().id());
            statement.setInt(3, prototype.semester());
            statement.setInt(4, prototype.numberOfStudyWeeks());
            statement.executeUpdate();
            statement.close();

            handleUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean tryUpdate(GroupModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE groups SET name = ?, speciality = ?, semester = ?, num_study_weeks = ? WHERE id = ?");
            statement.setString(1, model.name());
            statement.setInt(2, model.speciality().id());
            statement.setInt(3, model.semester());
            statement.setInt(4, model.numberOfStudyWeeks());
            statement.setInt(5, model.id());
            statement.executeUpdate();
            statement.close();

            handleUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }


}
