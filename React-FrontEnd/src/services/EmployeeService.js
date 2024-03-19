import axios from 'axios'

//서버에게 비동기 통신하는 여러 가지 방법, http 메소드를 사용해서 서버와 통신하는 방법
    //JSON 데이터로, XML 데이터로 (요새 잘 안 씀)
    //get(select), post(insert), put(update, 모든 필드), patch(update, 일부 필드), delete(delete)
/*
 *  1. XMLHTTPRequest: 초창기 사용, 사용 구분 복잡
 *  2. fetch: JavaScript 기본 내장, 라이브러리 설치없이 사용 가능
 *  3. ajax: JQuery 라이브러리 등록, <- MPA(Server Side Rendering)
 *  4. axios: React에서 많이 사용, 라이브러리 설치가 필요
 *      npm install axios
 *      yarn add axios
*/

const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:9999/api/employee';

class EmployeeService{

    getAllEmployees(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL)
    }

    createEmployee(employee){
        return axios.post(EMPLOYEE_BASE_REST_API_URL, employee)
    }

   getEmployeeById(employeeId){
       return axios.get(EMPLOYEE_BASE_REST_API_URL + '/' + employeeId);

    }

    updateEmployee(employeeId, employee){
        return axios.put(EMPLOYEE_BASE_REST_API_URL + '/' +employeeId, employee);
    }

    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_BASE_REST_API_URL + '/' + employeeId);
    }
}

export default new EmployeeService();