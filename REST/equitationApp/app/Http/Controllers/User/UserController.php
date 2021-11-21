<?php

namespace App\Http\Controllers\User;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\UserModel;
use App\Models\SeanceModel;
use App\Models\TaskModel;

class UserController extends Controller
{
    public function user(){
        return response()->json(UserModel::get(), 200);
    }
    
    public function adminLogin(){
        $users = UserModel::all();
        foreach($users as $user){
            if($user->email == request('email') && $user->password ==  request('password') && $user->userType == "ADMIN"){
                return response()->json(['login' => 'success', 'user' => $user]);
            }
        }
        return response()->json(['login' => 'failed']);
    }

    public function nonAdminLogin(){
        $users = UserModel::all();
        foreach($users as $user){
            if($user->email == request('email') && $user->password ==  request('password') && $user->userType != "ADMIN"){
                return response()->json(['login' => 'success', 'user' => $user]);
            }
        }
        return response()->json(['login' => 'failed']);
    }

    public function getSeancesAndTasks($userID){
        $seances = SeanceModel::where('monitorID', $userID);
        $tasks = TaskModel::where('user_Fk', $userID);
        return response(['seances' => $seances->get(), 'tasks' => $tasks->get()], 200);
    }
    
    public function getAllSeancesAndTasks(){
        return response(['seances' => SeanceModel::get(), 'tasks' => TaskModel::get()], 200);
    }
}
