import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListComponent } from './task-list/task-list.component';
import { AddTaskComponent } from './task-add/add-task.component';

const routes: Routes = [
  { path: '', redirectTo: 'view-task', pathMatch: 'full' },
  { path: 'view-task', component: TaskListComponent },
  { path: 'add-task', component: AddTaskComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
