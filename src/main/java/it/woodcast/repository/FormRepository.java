package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Integer> {

    public FormEntity findByFormName(String formName);



}
