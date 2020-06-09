package ir.eshragh.planning.modules.admins.service;

import ir.eshragh.planning.architecture.interfaces.service.ServiceInterface;
import ir.eshragh.planning.config.MyBeanCopy;
import ir.eshragh.planning.modules.admins.entity.Admin;
import ir.eshragh.planning.modules.admins.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class AdminService implements ServiceInterface<Admin> {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public List<Admin> findAllAdmin(){
        return this.adminRepository.findAll();
    }

    @Override
    @Transactional
    public Admin addNewItem(Admin admin){

        if(!admin.getPassword().isEmpty()){
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        }

        if(admin.getId() != 0){
            Admin exist = this.adminRepository.getOne(admin.getId());
            MyBeanCopy myBeanCopy = new MyBeanCopy();

            try {
                myBeanCopy.copyProperties(exist, admin);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return this.adminRepository.save(exist);
        }

        return this.adminRepository.save(admin);
    }

    @Override
    public Admin findById(Long id){
        if(this.adminRepository.existsById(id)){
            return this.adminRepository.getOne(id);
        }
        return new Admin();
    }

    @Transactional
    public void delete(long id) {
        if (this.adminRepository.existsById(id)) {
            Admin admin = this.adminRepository.getOne(id);
            this.adminRepository.delete(admin);
        }
    }

    @Override
    public Admin updateItem(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> deleteItem(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> findAllItem() {
        return null;
    }

    @Override
    public Admin findOne(Admin admin) {
        return null;
    }
}
