package com.example.todo.controller.api.projects;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectCreateService;

@WebMvcTest(ProjectCreateController.class)
class ProjectCreateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProjectCreateService projectCreateService;

  @Test
  void 作成されたProjectが返されること() throws Exception{
    //モックレスポンスの設定
    Project mockProject = new Project();
    mockProject.setId(1);
    mockProject.setName("New Project");
    mockProject.setSummary("This is a new project");


    Mockito.when(this.projectCreateService.invoke(Mockito.any(ProjectCreateRequest.class))).thenReturn(mockProject);

    // リクエストボディを作成
    String expectRequestBody = """
      {
          "name": "New Project",
          "summary": "This is a new project"
      }
      """;

     // テスト対象のコントローラの挙動を検証
    // mockMvc.perform()でHTTPリクエストをシミュレートし、コントローラの動作を検証できる。
    // post("/api/projects")でPOSTリクエストを送信し、contentType(MediaType.APPLICATION_JSON)でリクエストボディのContent-Typeを指定する。
    // content(this.expectRequestBody)でリクエストボディを指定する。
    // andExpect(status().isOk())でHTTPステータスが200であることを検証する。
    // andExpect(content().contentType(MediaType.APPLICATION_JSON))でレスポンスのContent-Typeがapplication/jsonであることを検証する。
    // andExpect(jsonPath("$.id").value(1))でレスポンスのidが1であることを検証する。
    // andExpect(jsonPath("$.name").value("New Project"))でレスポンスのnameが"New Project"であることを検証する。
    // andExpect(jsonPath("$.summary").value("This is a new project"))でレスポンスのsummaryが"This is a new
    // project"であることを検証する。
    this.mockMvc
    .perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(expectRequestBody))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.id").value(1))
    .andExpect(jsonPath("$.name").value("New Project"))
    .andExpect(jsonPath("$.summary").value("This is a new project"));
  }
}
