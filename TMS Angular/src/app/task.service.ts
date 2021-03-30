import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TaskService {

  private baseUrl = 'http://localhost:8080/api/task/';

  constructor(private http:HttpClient) { }

  getTaskList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'task-list');
  }

  createTask(task: object): Observable<object> {
    return this.http.post(`${this.baseUrl}`, task);
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete-task/${id}`, { responseType: 'text' });
  }

  getTask(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/task/${id}`);
  }

  updateTask(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update-task`, value);
  }
  
}                                           