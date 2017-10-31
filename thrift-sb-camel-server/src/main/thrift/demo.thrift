namespace java ru.redhat.forum.demo.gen


struct TestRequest {
  1: string name,
  2: i32 id
}

struct TestResponse {
  1: string name,
  2: i32 id
}


service DemoService {

   TestResponse TestCall(1:TestRequest req)  
}