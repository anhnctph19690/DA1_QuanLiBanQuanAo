package Services.Impl;

import DomainModels.ChucVuModel;
import Repository.IChucVurepository;
import Repository.Impl.ChucVuRepository;
import Services.IChucVuServicer;
import ViewModel.QLChucVu;
import java.util.ArrayList;
import java.util.List;

public class ChucVuServicer implements IChucVuServicer {

    IChucVurepository CVRepo;

    public ChucVuServicer() {

        CVRepo = new ChucVuRepository();
    }

    public List<QLChucVu> getList() {
        List<QLChucVu> listQlCV = new ArrayList<>();
        List<ChucVuModel> listCV = this.CVRepo.getAll();

        for (ChucVuModel cvMD : listCV) {
            QLChucVu qlCV = new QLChucVu(cvMD.getIdCV(), cvMD.getMaChucVu(),
                    cvMD.getChucVu(), cvMD.getTrangThai());

            listQlCV.add(qlCV);
        }
        return listQlCV;

    }

}
