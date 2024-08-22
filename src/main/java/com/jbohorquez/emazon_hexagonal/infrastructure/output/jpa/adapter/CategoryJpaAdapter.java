package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.domain.spi.ICategoryPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.*;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;

    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        //validar que no exista una categoria con el mismo nombre
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        //validar si nombre es mas corto que DESCRIPTION_MAX_LENGTH caracteres
        if (category.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException("Name is too long");
        }
        //validar si la descripcion es mas corta que DESCRIPTION_MAX_LENGTH caracteres
        if (category.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException("Description is too long");
        }
        //validar que name y description tengan contenido
        if (category.getName().isEmpty() || category.getDescription().isEmpty()) {
            throw new AllNotNameDescriptionNull("Name or description is empty");
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategory() {
            List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
            if (categoryEntityList.isEmpty()) {
                throw new NoDataFoundException();
            }
            return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(categoryId)
                .orElseThrow(CategoryAlreadyExistsException::new));
    }

    @Override
    public void updateCategory(Category category) {
        //validar que no exista una categoria con el mismo nombre
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Page<Category> getCategories(PageRequest pageRequest) {
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageRequest);
        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityPage.map(categoryEntityMapper::toCategory);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryEntityMapper::toCategory);
    }
}

