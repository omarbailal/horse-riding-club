<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

//client Routes
Route::get('client','App\Http\Controllers\Client\ClientController@client');
Route::get('clientById/{clientId}','App\Http\Controllers\Client\ClientController@getClient');
Route::post('clientLogin','App\Http\Controllers\Client\ClientController@login');
Route::post('addClient','App\Http\Controllers\Client\ClientController@addClient');

//seance Routes
Route::get('seance','App\Http\Controllers\Seance\SeanceController@seance');
Route::get('seanceClient/{clientID}','App\Http\Controllers\Seance\SeanceController@seanceByClient');
Route::get('seanceMonitor/{monitorID}','App\Http\Controllers\Seance\SeanceController@seanceByMonitor');
Route::put('updateSeance/{seanceID}','App\Http\Controllers\Seance\SeanceController@updateSeanceIsDone');
Route::put('updateSeanceDate/{seanceID}','App\Http\Controllers\Seance\SeanceController@updateSeanceDate');
Route::post('addSeance','App\Http\Controllers\Seance\SeanceController@addSeance');
Route::delete('deleteSeance/{seanceID}','App\Http\Controllers\Seance\SeanceController@deleteSeance');

//user Routes
Route::get('user','App\Http\Controllers\User\UserController@user');
Route::post('adminLogin','App\Http\Controllers\User\UserController@adminLogin');
Route::post('nonAdminLogin','App\Http\Controllers\User\UserController@nonAdminLogin');

//task Routs
Route::post('addTask','App\Http\Controllers\Task\TaskController@addTask');
Route::put('updateTask/{taskID}','App\Http\Controllers\task\TaskController@updateTaskIsDone');
Route::put('updateTaskDate/{taskID}','App\Http\Controllers\Task\TaskController@updateTaskDate');
Route::post('deleteTask/{taskID}','App\Http\Controllers\Task\TaskController@deleteTask');

//get seances and tasks
Route::get('seancesAndTasks/{userID}','App\Http\Controllers\User\UserController@getSeancesAndTasks');


