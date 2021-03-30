import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task';
import { Observable,Subject } from "rxjs";

import {FormControl,FormGroup,Validators} from '@angular/forms';
import { PropTaskObj } from '../propTaskObj';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

 constructor(private taskservice:TaskService) { }

  tasksArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  tasks: Observable<Task[]>;
  task : Task=new Task();
  deleteMessage=false;
  tasklist:any;
  isupdated = false;    
 

  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 6,
      stateSave:true,
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],
      processing: true
    };   
    this.taskservice.getTaskList().subscribe(data =>{
      let newTodo = Object.assign(new PropTaskObj(), data);
      this.tasks = newTodo.data;  
    this.dtTrigger.next();
    })
  }
  
  deleteTask(id: number) {
    this.taskservice.deleteTask(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.taskservice.getTaskList().subscribe(data =>{
            this.tasks =data
            })
        },
        error => console.log(error));
  }


  updateTask(id: number){
    this.taskservice.getTask(id)
      .subscribe(
        data => {
          let newTodo = Object.assign(new PropTaskObj(), data);
          this.task = newTodo.data;       
        },
        error => console.log(error));
  }

  taskupdateform=new FormGroup({
    id:new FormControl(),
    task_name:new FormControl('' , [Validators.required]),
    task_time:new FormControl('',[Validators.required]),
    task_group:new FormControl('' , [Validators.required]),
    task_assignee:new FormControl('',[Validators.required]),
    task_parent:new FormControl(),
    task_finished:new FormControl('',[Validators.required])
  });

  updateStu(updtask){
   this.task=new Task(); 
   this.task.id=this.TaskId.value;
   this.task.taskName=this.TaskName.value;
   this.task.timeSpentOnTask=this.TaskTime.value;
   this.task.taskGroup=this.TaskGroup.value;
   this.task.assignee=this.TaskAssignee.value;
   this.task.parentTask=this.TaskParent.value;
   this.task.finished=this.TaskFinish.value;

   this.taskservice.updateTask(this.task.id,this.task).subscribe(
    data => {     
      this.isupdated=true;
      this.taskservice.getTaskList().subscribe(data =>{
        this.tasks =data
        })
    },
    error => console.log(error));
  }

  get TaskId(){
    return this.taskupdateform.get('id');
  } 

  get TaskName(){
    return this.taskupdateform.get('task_name');
  }

  get TaskTime(){
    return this.taskupdateform.get('task_time');
  }

  get TaskGroup(){
    return this.taskupdateform.get('task_group');
  }

  get TaskAssignee(){
    return this.taskupdateform.get('task_assignee');
  }

  get TaskParent(){
    return this.taskupdateform.get('task_parent');
  }

  get TaskFinish(){
    return this.taskupdateform.get('task_finished');
  }
  

  changeisUpdate(){
    this.isupdated=false;
  }
}
