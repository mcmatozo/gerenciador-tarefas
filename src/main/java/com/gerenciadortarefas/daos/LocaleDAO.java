package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.Locale;

public class LocaleDAO {

    private Connection connection;

    public LocaleDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public void create(Locale locale) {

        String sql = "INSERT INTO localizacoes (name, location, user_id) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, locale.getName());
            stmt.setString(2, locale.getLocation());
            stmt.setInt(3, locale.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locale> list(int userId) {
        List<Locale> locales = new ArrayList<>();
        
        String sql = "SELECT * FROM localizacoes WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {        

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                int userIdLocale = rs.getInt("user_id");
                Locale locale = new Locale(id, userIdLocale, name, location);
                locales.add(locale);
                System.out.println(locale);
            } 
            return locales;
        } catch (SQLException e) {

            e.printStackTrace();
            return locales;
        }
    }

    public void delete(int localeId, int userId) {
        String removeLocaleInTakesSql = "UPDATE tarefas SET locale_id = null WHERE locale_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(removeLocaleInTakesSql)) {
            stmt.setInt(1, localeId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM localizacoes WHERE user_id = ? and id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, localeId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int userId, int localeId, String name, String address) {
        String sql = "UPDATE localizacoes SET name = ?, location = ? WHERE id = ? AND user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, localeId);
            stmt.setInt(4, userId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
