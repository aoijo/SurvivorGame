package UI;

import Enums.MapTile.MapType;
import InterfaceAdapter.*;
import InterfaceAdapter.BuffAdapter;
import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;

public class AdapterManager {
    private UseCaseManager useCaseManager;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private MapPresenter mapPresenter;
    private MapController mapController;
    private TimeAdapter timeAdapter;
    private TileAdapter tileAdapter;
    private ItemAdapter itemAdapter;
    private SkillAdapter skillAdapter;
    private BuffAdapter buffAdapter;
    private long mapSeed = 12345L;

    public AdapterManager(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
        playerController = new PlayerController(useCaseManager);
        playerPresenter = new PlayerPresenter(useCaseManager, playerController);
        mapPresenter = new MapPresenter(useCaseManager);
        mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, useCaseManager);
        timeAdapter = new TimeAdapter(useCaseManager);
        tileAdapter = new TileAdapter(useCaseManager);
        itemAdapter = new ItemAdapter(useCaseManager);
        skillAdapter = new SkillAdapter(useCaseManager);
        buffAdapter = new BuffAdapter(useCaseManager);
    }
    public UseCaseManager getUseCaseManager() {
        return useCaseManager;
    }
    public void setUseCaseManager(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public PlayerPresenter getPlayerPresenter() {
        return playerPresenter;
    }

    public void setPlayerPresenter(PlayerPresenter playerPresenter) {
        this.playerPresenter = playerPresenter;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public MapPresenter getMapPresenter() {
        return mapPresenter;
    }

    public void setMapPresenter(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }

    public MapController getMapController() {
        return mapController;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public TimeAdapter getTimeAdapter() {
        return timeAdapter;
    }

    public void setTimeAdapter(TimeAdapter timeAdapter) {
        this.timeAdapter = timeAdapter;
    }

    public TileAdapter getTileAdapter() {
        return tileAdapter;
    }

    public void setTileAdapter(TileAdapter tileAdapter) {
        this.tileAdapter = tileAdapter;
    }

    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }

    public SkillAdapter getSkillAdapter() {
        return skillAdapter;
    }

    public BuffAdapter getBuffAdapter() {
        return buffAdapter;
    }
}
