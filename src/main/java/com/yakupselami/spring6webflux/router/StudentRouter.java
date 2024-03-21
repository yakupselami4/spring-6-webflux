package com.yakupselami.spring6webflux.router;



import com.yakupselami.spring6webflux.dto.StudentListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class StudentRouter {

    public static final String STUDENT_ROUTE="/v1/student";
    public static final String STUDENT_COURSE_ROUTER="/courses";
    private final StudentHandler studentHandler;

    public StudentRouter(StudentHandler studentHandler) {
        this.studentHandler = studentHandler;
    }
    @Bean
    @RouterOperations(
            @RouterOperation(
                    path= STUDENT_COURSE_ROUTER,
                    method = RequestMethod.GET,
                    operation=
                            @Operation(
                                    operationId = "findAllStudentWithCourses",
                                    summary = "Find all students with courses",
                                    description = "Returns a list of students with their courses",
                                    responses=
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Success",
                                                    content = @Content(schema = @Schema(implementation = StudentListDto.class))
                                            )
                            )

            )
    )
    public RouterFunction<ServerResponse> findAllStudentWithCourses() {
        return RouterFunctions.nest(
                path(STUDENT_ROUTE),
                RouterFunctions.route(
                        GET(STUDENT_COURSE_ROUTER).and(accept(MediaType.APPLICATION_JSON)),
                        studentHandler::handleFindAllStudentWithCourses));

    }

}
