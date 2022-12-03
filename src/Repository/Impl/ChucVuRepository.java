package Repository.Impl;

import DomainModels.ChucVuModel;
import Repository.IChucVurepository;
import Ultilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChucVuRepository implements IChucVurepository {

    @Override
    public List<ChucVuModel> getAll() {
        ArrayList<ChucVuModel> listChucVu = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM ChucVu";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next() == true) {
                String id = rs.getString("IdCV");
                String maCV = rs.getString("MaChucVu");
                String CV = rs.getString("ChucVu");
                Integer trangThai = rs.getInt("TrangThai");

                ChucVuModel cv = new ChucVuModel(CV, maCV, CV, trangThai);
                listChucVu.add(cv);
            }

            System.out.println("Select ok");
        } catch (Exception e) {
            System.out.println("Select Xsadfg");
            e.printStackTrace();
        }
        return listChucVu;

    }

}
