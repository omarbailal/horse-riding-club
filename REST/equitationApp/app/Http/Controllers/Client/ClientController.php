<?php

namespace App\Http\Controllers\Client;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\ClientModel;


class ClientController extends Controller
{
    public function login(){
        $clients = ClientModel::all();
        foreach($clients as $client){
            if($client->email == request('email') && $client->password ==  request('password')){
                return response()->json(['login' => 'success', 'client' => $client]);
            }
        }
        return response()->json(['login' => 'failed']);
    }

    public function client(){
        return response()->json(ClientModel::get(), 200);
    }

    public function getClient($clientID){
        $client = ClientModel::where('clientID', $clientID);
        return response()->json($client->get(), 200);
    }
    
    public function addClient(){
        $client = new ClientModel();
        $client->fName = request('fName');
        $client->lName = request('lName');
        $client->ensurenceValidity = date('Y-m-d H:i:s');
        $client->licenceValidity = date('Y-m-d H:i:s');
        $client->email = request('email');
        $client->password = request('password');
        $client->clientPhone = request('clientPhone');
        $client->priceRate = 1;
        $client->notes = "test";
        $client->save();
        return response()->json($client->get(), 201);
    }
    

}
