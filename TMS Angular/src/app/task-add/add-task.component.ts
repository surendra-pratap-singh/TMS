import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import {FormControl,FormGroup,Validators} from '@angular/forms';
import { Task } from '../task';
@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  constructor(private taskservice:TaskService) { }

  task : Task=new Task();
  submitted = false;

  ngOnInit() {
    this.submitted=false;
  }

  tasksaveform=new FormGroup({
    task_name:new FormControl('' , [Validators.required]),
    task_time:new FormControl('',[Validators.required]),
    task_group:new FormControl('' , [Validators.required]),
    task_assignee:new FormControl('',[Validators.required]),
    task_parent:new FormControl(),
    task_finish:new FormControl('',[Validators.required])
  });

  saveTask(saveTask){
    this.task=new Task();   
    this.task.taskName =this.TaskName.value;
    this.task.timeSpentOnTask=this.TaskTime.value;
    this.task.taskGroup=this.TaskGroup.value;
    this.task.assignee=this.TaskAssignee.value;
    this.task.parentTask=this.TaskParent.value;
    this.task.finished=this.TaskFinish.value;
    this.submitted = true;
    this.save();
  }

  

  save() {
    this.taskservice.createTask(this.task)
      .subscribe(data => console.log(data), error => console.log(error));
    this.task = new Task();
    
  }

  get TaskName(){
    return this.tasksaveform.get('task_name');
  }

  get TaskTime(){
    return this.tasksaveform.get('task_time');
  }

  get TaskGroup(){
    return this.tasksaveform.get('task_group');
  }

  get TaskAssignee(){
    return this.tasksaveform.get('task_assignee');
  }

  get TaskParent(){
    return this.tasksaveform.get('task_parent');
  }

  get TaskFinish(){
    return this.tasksaveform.get('task_finish');
  }


  addTaskForm(){
    this.submitted=false;
    this.tasksaveform.reset();
  }
}
