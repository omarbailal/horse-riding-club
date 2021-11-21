<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

class CreateSeancesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('seances', function(Blueprint $table)
		{
			$table->increments('id');
			$table->integer('seanceGrpID')->unsigned();
			$table->integer('clientID')->unsigned()->index('clientID');
			$table->smallInteger('monitorID')->unsigned()->index('userID_FKY');
			$table->timestamp('startDate')->default(DB::raw('CURRENT_TIMESTAMP'))->index('startDate');
			$table->integer('durationMinut')->default(120);
			$table->integer('isDone')->default(0);
			$table->integer('paymentID')->unsigned()->nullable()->default(0);
			$table->string('comments', 200);
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('seances');
	}

}
