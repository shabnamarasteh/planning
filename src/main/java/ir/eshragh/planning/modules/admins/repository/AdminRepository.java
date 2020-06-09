package ir.eshragh.planning.modules.admins.repository;

import ir.eshragh.planning.architecture.interfaces.repository.RepositoryInterface;
import ir.eshragh.planning.modules.admins.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> , RepositoryInterface<Admin> {
    Admin findByUsername(String username);
}