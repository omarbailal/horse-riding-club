<?php

namespace App\Http\Controllers\Task;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\TaskModel;

class TaskController extends Controller
{
    public function addTask(){
        $Task = new TaskModel();
        $Task->startDate = request('startDate');
        $Task->durationMinut = request('durationMinut');
        $Task->title = request('title');
        $Task->detail = request('detail');
        $Task->user_Fk = request('user_Fk');
        $Task->save();
        return response()->json($Task->get(), 201);
    }

    public function updateTaskIsDone($taskID){
        $tasks = TaskModel::all();
        foreach($tasks as $task){
            if($task->id == $taskID){
                $task->isDone = request('isDone');
                $task->save();
                return response()->json(['msg'=>'success','task' => $task], 201);
            }
        }
        return response()->json(['msg' => 'error'], 404);
    }
    public function updateTaskDate($taskID){
        $tasks = TaskModel::all();

        foreach($tasks as $task){
            if($task->id == $taskID){
                $task->startDate = request('startDate');
                $task->save();
                return response()->json(['msg'=>'success','task' => $task], 201);
            }
        }
        return response()->json(['msg' => 'error'], 404);
    }
    public function deleteTask($taskID){
        $tasks = TaskModel::all();
        foreach($tasks as $task){
            if($task->id == $taskID){
                $task->delete();
                return response()->json("task deleted", 201);
            }
        }
    }
    
}

