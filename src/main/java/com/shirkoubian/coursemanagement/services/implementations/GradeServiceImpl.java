package com.shirkoubian.coursemanagement.services.implementations;

import com.shirkoubian.coursemanagement.dtos.EntityMapper;
import com.shirkoubian.coursemanagement.dtos.GradeDto;
import com.shirkoubian.coursemanagement.dtos.Response;
import com.shirkoubian.coursemanagement.entities.Grade;
import com.shirkoubian.coursemanagement.exception.NotFoundException;
import com.shirkoubian.coursemanagement.repositories.GradeRepository;
import com.shirkoubian.coursemanagement.services.interfaces.GradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }


    @Override
    public Response<GradeDto> createGrade(GradeDto gradeDto) {
        Grade grade = EntityMapper.toGradeEntity(gradeDto);
        Grade savedGrade = gradeRepository.save(grade);

        return Response.<GradeDto>builder()
                .message("نمره برای درس ذخیره شد!")
                .statusCode(HttpStatus.CREATED.value())
                .data(EntityMapper.toGradeDTO(savedGrade))
                .build();
    }

    @Override
    public Response<GradeDto> updateGrade(GradeDto gradeDto, long gradeId) {
        Grade gradeToUpdate;
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if (grade.isPresent()) {
            gradeToUpdate = EntityMapper.toGradeEntity(gradeDto);
            gradeToUpdate.setId(grade.get().getId());
            Grade updatedGrade = gradeRepository.save(gradeToUpdate);
            return Response.<GradeDto>builder()
                    .message("نمره به روزرسانی شد!")
                    .statusCode(HttpStatus.OK.value())
                    .data(EntityMapper.toGradeDTO(updatedGrade))
                    .build();
        } else
            throw new NotFoundException("نمره با شناسه " + gradeId + " پیدا نشد!");
    }

    @Override
    public Response<Set<GradeDto>> getGrades(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Grade> gradePage = gradeRepository.findAll(pageable);

        return Response.<Set<GradeDto>>builder()
                .message("نمرات:")
                .statusCode(HttpStatus.OK.value())
                .data(gradePage.get().map(EntityMapper::toGradeDTO).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Response<String> deleteGrade(long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new NotFoundException("نمره با شناسه " + gradeId + " پیدا نشد!"));
        gradeRepository.delete(grade);
        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("نمره حذف شد!")
                .build();
    }
}
