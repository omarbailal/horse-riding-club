<?php

namespace App\Http\Controllers\Seance;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\SeanceModel;

class SeanceController extends Controller
{
    public function seance(){
        return response()->json(SeanceModel::get(), 200);
    }
    public function seanceByClient($clientID){
        $seances = SeanceModel::where('clientID', $clientID);
        return response()->json($seances->get(), 200);
    }
    public function seanceByMonitor($monitorID){
        $seances = SeanceModel::where('monitorID', $monitorID);
        return response()->json($seances->get(), 200);
    }

    public function updateSeanceDate($seanceID){
        $seances = SeanceModel::all();

        foreach($seances as $seance)
            if($seance->startDate == request('startDate') && $seance->clientID == request('clientID'))
                return response()->json(['msg' => 'this date already have a seance for client'], 404);

        foreach($seances as $seance){
            if($seance->id == $seanceID){
                $seance->startDate = request('startDate');
                $seance->save();
                return response()->json(['msg'=>'success','seance' => $seance], 201);
            }
        }
        return response()->json(['msg' => 'error'], 404);
    }

    public function updateSeanceIsDone($seanceID){
        $seances = SeanceModel::all();
        foreach($seances as $seance){
            if($seance->id == $seanceID){
                $seance->isDone = request('isDone');
                $seance->save();
                return response()->json(['msg'=>'success','seance' => $seance], 201);
            }
        }
        return response()->json(['msg' => 'error'], 404);
    }

    public function addSeance(){
        $seance = new SeanceModel();
        $seance->clientID = request('clientID');
        $seance->monitorID = request('monitorID');
        $seance->startDate = request('startDate');
        $seance->durationMinut = request('duration');
        $seance->save();
        return response()->json($seance->get(), 201);
    }

    public function deleteSeance($seanceID){
        $seances = SeanceModel::all();
        foreach($seances as $seance){
            if($seance->id == $seanceID){
                $seance->delete();
                return response()->json(null, 201);
            }
        }
    }


}
